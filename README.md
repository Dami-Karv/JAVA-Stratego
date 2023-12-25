# Group fairnes scheduling algo in the linux kernel

## Εισαγωγή

### Λειτουργικό Σύστημα
- **Version**: Linux 2.6.38.1

### Αρχική Λογική της Άσκησης
- Ο αλγόριθμος “Group Fairness” απαιτεί ότι κάθε διεργασία πρέπει να δηλώνει το group στο οποίο ανήκει. Κάθε group λαμβάνει ίσο μερίδιο του συνολικού χρόνου επεξεργαστή. 
- Ο χρόνος εκτέλεσης κάθε διεργασίας υπολογίζεται με βάση τον αριθμό των διεργασιών που ανήκουν στο ίδιο group.
- Για παράδειγμα, αν έχουμε δύο groups, A και Β, με τις διεργασίες Α1, Α2, και Α3 στο group A και τις διεργασίες Β1 και Β2 στο group B, ο scheduler πρέπει να διασφαλίσει ότι η διεργασία Α1 θα λάβει 100/2/3 = 16.6% του χρόνου, η Α2 θα λάβει 100/2/3 = 16.6%, και η Α3 θα λάβει 100/2/3 = 16.6% του συνολικού χρόνου επεξεργασίας. Αντίστοιχα, η Β1 και Β2 θα λάβουν από 25% του συνολικού χρόνου, ή 100/2/2.
- Η εξίσωση υπολογισμού χρόνου για κάθε διεργασία είναι: Τ(process_params, number_of_groups) = 100/number_of_groups/number_of_processes_in_group(process_params.group_name)



## Βήματα Υλοποίησης
  ### Κατανόηση συστηματος
 
   **Η Λειτουργία schedule**
   
   Η schedule(void) είναι η κύρια λειτουργία του Scheduler στον πυρήνα του Linux.Αυτή η λειτουργία καθορίζει ποια διεργασία θα εκτελεστεί επόμενη στον επεξεργαστή. Λειτουργεί με βάση την πολιτική χρονοδρομολόγησης που έχει οριστεί, επιλέγοντας την κατάλληλη διεργασία από την ουρά εκτέλεσης.


   **Run Queue**
   
   Η Run Queue είναι μια δομή δεδομένων που χρησιμοποιείται από τον Scheduler για τη διαχείριση των διεργασιών που πρέπει να εκτελεστούν.Περιέχει πληροφορίες για τις διεργασίες που είναι έτοιμες για εκτέλεση ή περιμένουν να εκτελεστούν.Ο Scheduler επιλέγει τις διεργασίες από αυτή την ουρά με βάση την τρέχουσα πολιτική χρονοδρομολόγησης.


   **Sched_entity**
   
   Η δομή Sched_entity αντιπροσωπεύει μια αφαιρετική ενότητα που μπορεί να χρονοδρομολογηθεί από τον Scheduler.Περιλαμβάνει πληροφορίες σχετικές με την εκτέλεση της διεργασίας, όπως τον χρόνο εκτέλεσης, τις προτεραιότητες και τις καταστάσεις ύπνου.Η Sched_entity είναι κρίσιμη για την υλοποίηση πιο σύνθετων πολιτικών χρονοδρομολόγησης, καθώς παρέχει την ευελιξία στον Scheduler να διαχειριστεί διάφορες πτυχές της διεργασίας.


   **Completely Fair Scheduling**
   
   Το Completely Fair Scheduling (CFS) είναι μια πολιτική χρονοδρομολόγησης που εστιάζει στη δίκαιη κατανομή του χρόνου επεξεργαστή μεταξύ των διεργασιών.Στοχεύει στην εξισορρόπηση του χρόνου επεξεργασίας, διασφαλίζοντας ότι κάθε διεργασία λαμβάνει ένα δίκαιο μερίδιο του διαθέσιμου χρόνου.Η λειτουργία του CFS βασίζεται στη χρήση ενός εικονικού χρόνου για να καθορίσει πόσο χρόνο έχει αφιερώσει κάθε διεργασία και να ρυθμίσει την προτεραιότητά της ανάλογα.



## Δημιουργία νέας πολιτικής

Η προσέγγισή μου στη λογική της άσκησης ήταν η χρήση των `get` και `set` params για τη δημιουργία πολλών demo προγραμμάτων με τυχαίο αριθμό και χρόνο εκτέλεσης, τα οποία θα τοποθετούνταν σε ομάδες με τυχαία σειρά. Όλα τα demo προγράμματα θα ξεκινούσαν από την αρχή (με την επιλογή να προστεθούν και άλλα να ξεκινήσουν αργότερα) και θα εκτελούνταν μέχρι να τελειώσει ο χρόνος τους σε ticks (στη συγκεκριμένη έκδοση 1000 ticks = 1 sec). Κάθε demo διαδικασία θα λάμβανε 1 tick ανά κύκλο, τα οποία θα μοιράζονταν μεταξύ τους ανάλογα με το scheduling. Για παράδειγμα:

- Process A1 με 50% του tick και χρόνο 5 ticks
- Process B1 με 25% του tick και χρόνο 2 ticks
- Process B2 με 25% του tick και χρόνο 1 tick

Στον 4ο κύκλο, θα τελείωνε το B2 και θα γινόταν rescheduling, όπου:

- Process A1 με 50% του tick και εναπομείναντα χρόνο 3 ticks
- Process B1 με 50% του tick και εναπομείναντα χρόνο 1 tick

Αυτή η διαδικασία θα συνεχιζόταν μέχρι να τελειώσουν όλες οι διεργασίες και, ανάλογα με τη δημιουργία νέων processes, θα παρακολουθούσαμε τα πάντα μέσω `printk`.



 
  




## Χρήση Αλλαγών από την Άσκηση 3

Η χρήση των set/get params που αναπτύχθηκαν στην Άσκηση 3 ήταν βασική στη λογική μου, καθώς μου επέτρεψε να ρυθμίσω ένα συγκεκριμένο χρονικό διάστημα σε ticks για κάθε διεργασία των demo προγραμμάτων.

### Τροποποίηση της Δομής d_params

```c
#ifndef _DPARAMS_H
#define _DPARAMS_H

struct d_params {
    char group_name;
    int member_id;
    unsigned long time_in_ticks; // Νέο πεδίο για τον χρόνο
};
#endif /* _DPARAMS_H */
 ```

### Τροποποίηση της Δομής setparams

  ```c
     #include <linux/kernel.h>
#include <linux/sched.h>
#include <linux/syscalls.h>
#include <linux/uaccess.h>
#include <linux/dparams.h>

asmlinkage long sys_set_task_params(char group_name, int member_id, unsigned long time_in_ticks) {
    struct task_struct *task;
    
    printk("csd4897 - set_task_params called\n");
    if (group_name < 'A' || group_name > 'Z' || member_id <= 0) {
        return -EINVAL;
    }

    task = current;
    task_lock(task);

    
    task->group_name = group_name;
    task->member_id = member_id;
    task->time_in_ticks = time_in_ticks;

   
    task->sched_group_info  = get_or_create_group(group_name); 

    task_unlock(task);
    
    return 0;
}
```

Στην αλλαγμένη έκδοση της sys_set_task_params, πρόσθεσα τη δυνατότητα να ορίζω ένα χρονικό διάστημα σε ticks για κάθε διεργασία. Αυτή η προσθήκη είναι κρίσιμη για τη λειτουργία της νέας πολιτικής μου, καθώς επιτρέπει στις διεργασίες να έχουν ένα προκαθορισμένο χρονικό διάστημα εκτέλεσης. Εισήγαγα ένα νέο πεδίο time_in_ticks στη δομή task_struct του πυρήνα, το οποίο αποθηκεύει τον χρόνο εκτέλεσης που έχει οριστεί για κάθε διεργασία.Ενημέρωσα τη task_struct με τις τιμές group_name, member_id, και time_in_ticks που παρέχονται από την κλήση της συνάρτησης.

### Τροποποίηση της Δομής getparams

```c
   #include <linux/kernel.h>
#include <linux/sched.h>
#include <linux/syscalls.h>
#include <linux/uaccess.h>
#include <linux/dparams.h>

asmlinkage long sys_get_task_params(struct d_params __user *params) {
    struct task_struct *task;
    struct d_params kernel_params;

    printk("csd4897 - get_task_params called\n");
    if (!access_ok(VERIFY_READ, params, sizeof(struct d_params))) {
        return -EFAULT;
    }

    task = current; // Initialize 'task' with 'current'
    task_lock(task);

   kernel_params.group_name = task->group_name;

    kernel_params.member_id = task->member_id;
    kernel_params.time_in_ticks = task->time_in_ticks;

    task_unlock(task);

    if (copy_to_user(params, &kernel_params, sizeof(struct d_params))) {
        return -EFAULT;
    }

    return 0;
}
```


Στην sys_get_task_params, πρόσθεσα τη δυνατότητα να ανακτώ το χρονικό διάστημα σε ticks που έχει οριστεί για κάθε διεργασία, εκτός από το group id και το member id. Ενσωμάτωσα κώδικα για να αντιγράψω τις τιμές group_name, member_id, και time_in_ticks από την task_struct στη δομή d_params που επιστρέφεται στον χρήστη. Χρησιμοποίησα τη συνάρτηση copy_to_user για να αντιγράψω ασφαλώς τις τιμές στο χώρο διεργασιών του χρήστη.
  
   - 
## Τροποποίηση των αρχειων
   - Στην υλοποιηση μου αποσκοπουσα μονο να μετατρεψω καποια αρχεια του kernel χωρις να προσθεσω νεα αρχεια , συγκεκριμενα τα
     - sched.c
     - sched.h
     - syscalls.h
       (απο εδω και κατω αρχεια που θα επρεπε να μετατρεψω αλλα δεν μετετρεψα)
     - Kconfig
     - sched_rt.c
     - process.c
   
## Νεες συναρτησεις και αλλαγμενες kernel συναρτησεις

### calculate time slices

```c
static LIST_HEAD(group_list); unsigned long calculate_time_slice(struct task_struct *task, struct sched_group_info *group) {
    unsigned long group_time_slice;
    int number_of_processes_in_group = 0;
    struct task_struct *tmp_task;

    group_time_slice = group->total_group_time;
    list_for_each_entry(tmp_task, &group->process_list, tasks) {
        number_of_processes_in_group++;
    }

        if (number_of_processes_in_group == 0) {
        printk(KERN_INFO "Group Fairness: Group %c has no processes.\n", group->group_name);
        return 0;
    }

       unsigned long time_slice = group_time_slice / number_of_processes_in_group;

        printk(KERN_INFO "Group Fairness: Task %s (PID: %d) in Group %c gets time slice: %lu\n", 
           task->comm, task->pid, group->group_name, time_slice);

    return time_slice;
}
```

## Υπολογισμός Αριθμού Διεργασιών στην Ομάδα

### Επισκόπηση

Η συνάρτηση `calculate_time_slice` είναι σχεδιασμένη για να υπολογίζει το χρονικό κουάντουμ για κάθε διεργασία σε μια συγκεκριμένη ομάδα, βασιζόμενη στην πολιτική "Group Fairness".

### Βασικές Λειτουργίες

#### 1. **Διασχίζοντας την Ομάδα Διεργασιών**

Μέσω του βρόχου `list_for_each_entry`, η συνάρτηση διατρέχει όλες τις διεργασίες που ανήκουν στην ομάδα `group->process_list`, και αυξάνει τον αριθμό των διεργασιών στην μεταβλητή `number_of_processes_in_group`.

#### 2. **Έλεγχος για Κενή Ομάδα**

Εάν δεν υπάρχουν διεργασίες στην ομάδα (`number_of_processes_in_group` είναι 0), εκτυπώνεται μήνυμα στον πυρήνα και η συνάρτηση επιστρέφει 0, δηλαδή δεν υπάρχει ανάγκη για υπολογισμό χρονικού κουάντουμ.

#### 3. **Υπολογισμός Χρονικού Κουάντουμ για τη Διεργασία**

Για τις διεργασίες που υπάρχουν στην ομάδα, η συνάρτηση υπολογίζει το χρονικό κουάντουμ (`time_slice`) διαιρώντας το συνολικό διαθέσιμο χρόνο της ομάδας (`group_time_slice`) με τον αριθμό των διεργασιών στην ομάδα.

#### 4. **Εκτύπωση Πληροφοριών για Debugging**

Εκτυπώνει πληροφορίες σχετικά με τη διεργασία και το υπολογισμένο χρονικό κουάντουμ, περιλαμβάνοντας το όνομα της διεργασίας (`task->comm`), το PID (`task->pid`), το όνομα της ομάδας (`group->group_name`), και το υπολογισμένο χρονικό κουάντουμ.

### Συμπερασματικά

Η συνάρτηση `calculate_time_slice` διασφαλίζει μια δίκαιη κατανομή των πόρων μεταξύ των διεργασιών μιας ομάδας, ακολουθώντας την πολιτική "Group Fairness".



### Αλλαγες στην schedule

```c
asmlinkage void __sched schedule(void)
{
    struct task_struct *prev, *next;
    unsigned long *switch_count;
    struct rq *rq;
    int cpu;

    // Custom Group Fairness Variables
    struct sched_group_info  *group;
    unsigned long highest_time_slice = 0;
    struct task_struct *candidate_task = NULL;

need_resched:
    preempt_disable();
    cpu = smp_processor_id();
    rq = cpu_rq(cpu);
    rcu_note_context_switch(cpu);
    prev = rq->curr;

    release_kernel_lock(prev);
need_resched_nonpreemptible:

    schedule_debug(prev);

    if (sched_feat(HRTICK))
        hrtick_clear(rq);

    raw_spin_lock_irq(&rq->lock);

    switch_count = &prev->nivcsw;
    if (prev->state && !(preempt_count() & PREEMPT_ACTIVE)) {
		if (unlikely(signal_pending_state(prev->state, prev))) {
			prev->state = TASK_RUNNING;
		} else {
			/*
			 * If a worker is going to sleep, notify and
			 * ask workqueue whether it wants to wake up a
			 * task to maintain concurrency.  If so, wake
			 * up the task.
			 */
			if (prev->flags & PF_WQ_WORKER) {
				struct task_struct *to_wakeup;

				to_wakeup = wq_worker_sleeping(prev, cpu);
				if (to_wakeup)
					try_to_wake_up_local(to_wakeup);
			}
			deactivate_task(rq, prev, DEQUEUE_SLEEP);
		}
		 switch_count = &prev->nvcsw;
    }

    pre_schedule(rq, prev);

    if (unlikely(!rq->nr_running))
        idle_balance(cpu, rq);

    put_prev_task(rq, prev);

    // Custom Group Fairness Scheduler Logic
    list_for_each_entry(group, &group_list, process_list) {
        struct task_struct *task;
        unsigned long time_slice;

        list_for_each_entry(task, &group->process_list, tasks) {
            time_slice = calculate_time_slice(task, group);
            if (time_slice > highest_time_slice) {
                highest_time_slice = time_slice;
                candidate_task = task;
            }
        }
    }

    next = candidate_task ? candidate_task : pick_next_task(rq);
    clear_tsk_need_resched(prev);
    rq->skip_clock_update = 0;

    if (likely(prev != next)) {
        sched_info_switch(prev, next);
        perf_event_task_sched_out(prev, next);

        rq->nr_switches++;
        rq->curr = next;
        ++*switch_count;

        context_switch(rq, prev, next); // unlocks the rq
        cpu = smp_processor_id();
        rq = cpu_rq(cpu);
    } else
        raw_spin_unlock_irq(&rq->lock);

    post_schedule(rq);

    if (unlikely(reacquire_kernel_lock(prev)))
        goto need_resched_nonpreemptible;

    preempt_enable_no_resched();
    if (need_resched())
        goto need_resched;
}
EXPORT_SYMBOL(schedule);
```


## Εισαγωγή Νέων Μεταβλητών για την "Group Fairness"

Προσέθεσα νέες μεταβλητές στην υλοποίηση του scheduling αλγορίθμου, όπως `group`, `highest_time_slice`, και `candidate_task`. Αυτές οι μεταβλητές χρησιμοποιήθηκαν για να διαχειριστώ τις ομάδες διεργασιών και να επιλέξω την κατάλληλη διεργασία για εκτέλεση βάσει της πολιτικής "Group Fairness".

### Υλοποίηση Λογικής Επιλογής Διεργασίας

Μέσω ενός βρόχου `list_for_each_entry`, διασχίζω κάθε ομάδα διεργασιών και κάθε διεργασία μέσα σε κάθε ομάδα. Για κάθε διεργασία, υπολογίζω το χρονικό κουάντουμ με την `calculate_time_slice`. Εάν βρω μια διεργασία με μεγαλύτερο χρονικό κουάντουμ από ό,τι έχει εντοπιστεί μέχρι τότε, την ορίζω ως τον υποψήφιο για την επόμενη εκτέλεση.

### Επιλογή της Επόμενης Διεργασίας για Εκτέλεση

Αν έχω βρει έναν υποψήφιο (`candidate_task`), τότε αυτός επιλέγεται για την επόμενη εκτέλεση. Σε διαφορετική περίπτωση, χρησιμοποιώ την συνάρτηση `pick_next_task` για να επιλέξω την επόμενη διεργασία.

### Γενική Λειτουργία της Συνάρτησης `schedule`

#### Αρχική Διαδικασία Χρονοδρομολόγησης

Απενεργοποιώ την προληπτική διακοπή και καθορίζω τον τρέχοντα επεξεργαστή και την ουρά εκτέλεσης (`rq`) για αυτόν.

#### Διαχείριση Τρέχουσας Διεργασίας

Ελέγχω την κατάσταση της τρέχουσας διεργασίας (`prev`) και αν χρειάζεται, την απενεργοποιώ από την εκτέλεση.

#### Εκτέλεση Εναλλαγής Περιεχομένου

Επιλέγω την επόμενη διεργασία για εκτέλεση (`next`) και πραγματοποιώ εναλλαγή περιεχομένου αν η τρέχουσα διεργασία διαφέρει από την επόμενη.



## Προβλήματα και Λύσεις

### Κύριο Θέμα: Ασυμβατότητα στο sched.c

Το κύριο πρόβλημα που αντιμετώπισα ήταν ότι ο `sched.c` δεν μπορούσε να αναγνωρίσει κάποιες δομές από το `sched.h`. Κατά την προσπάθεια συγκέντρωσης του πυρήνα του Linux για την υλοποίηση της πολιτικής "Group Fairness", αντιμετώπισα σημαντικά προβλήματα στον κώδικα που επηρέασαν τη δομή `task_struct`. 

#### Σφάλματα Αναφοράς σε Ανολοκλήρωτους Τύπους

Μηνύματα λάθους όπως το `error: dereferencing pointer to incomplete type` υποδήλωναν προσπάθειες προσπέλασης ή τροποποίησης μελών της δομής `task_struct` μέσω ενός δείκτη που δεν ήταν πλήρως ορισμένος. Αυτό συνέβη επειδή προσέθεσα νέα πεδία στη δομή `task_struct`, όπως `group_name`, `member_id`, και `time_in_ticks`, αλλά δεν διασφάλισα την ορθή αναφορά και ενημέρωση αυτών των πεδίων στον πυρήνα.

#### Προειδοποιήσεις για Ασυμβατότητα Τύπων

Προειδοποιήσεις όπως `warning: initialization from incompatible pointer type` έδειξαν πιθανή χρήση λανθασμένων τύπων δεικτών ή λάθη στις μετατροπές τύπων, συνδεόμενα με τις αλλαγές στη δομή `task_struct`.

#### Σφάλματα στον Κώδικα

Σφάλματα στη συνάρτηση `calculate_time_slice` του αρχείου `include/linux/sched.h` όπως `error: dereferencing pointer to incomplete type` υποδηλώνουν ότι οι τροποποιήσεις στη δομή `task_struct` δεν ήταν συμβατές με την υπόλοιπη δομή και λογική του πυρήνα.



## Προσπαθειες επιλυσης
Οσες δοκιμες εγιναν για αλλαγη και διορθωση του κωδικα αναδειχθησαν ανουσιες καθως το linking θεμα ηταν ριζωμενο αλλου και η ριζικη διορθωση του θα απαιτουσε υποχωρηση σε εκδωση με αλλαγμενο get και set πριν τις αλλαγες στο syscall , sched.c , .h αλλα δεν κρατηθηκε πουθενα backup και λογω χρονου δεν εγινε η προσπαθεια
Εφοσον δεν μπορυσε καν να κανει compile δεν δωθηκε και bzimage


## Παραπομπές
https://en.wikipedia.org/wiki/Linux_kernel

https://man7.org/linux/man-pages/man7/sched.7.html

https://www.baeldung.com/linux/real-time-process-scheduling

https://docs.kernel.org/scheduler/sched-rt-group.html
