# Group fairnes scheduling algo in the linux kernel

## Εισαγωγή
- **Λειτουργικό Σύστημα**: Linux 2.6.38.1
- Αρχικη λογικη ασκησης ¨
   Σύμφωνα με τον αλγόριθμο “Group Fairness” όλες οι διεργασίες οφείλουν να δηλώνουν το group στο οποίο ανήκουν. Κάθε group παίρνει ίσο ποσοστό του συνολικού χρόνου. Στη συνέχεια ο χρόνος εκτέλεσης της κάθε διεργασίας υπολογίζεται με βάση τον αριθμό των διεργασιών που βρίσκονται στο group. Συγκεκριμένα, αν έχουμε 2 groups, A και Β, με τις διεργασίες Α1, Α2 και Α3 στο group A και τις διεργασίες Β1 και Β2 στο group B, ο scheduler πρέπει να βεβαιωθεί ότι η διεργασία Α1 θα πάρει 100/2/3 = 16.6% του χρόνου, η Α2 θα πάρει 100/2/3 = 16.6% και η Α3 θα πάρει 100/2/3=16.6% του συνολικού χρόνου του επεξεργαστή. Από την άλλη η Β1 και Β2 θα πάρουν η κάθε μια 25% του συνολικού χρόνου που ισοδυναμεί με 100/2/2.  Η εξίσωση που υπολογίζει το χρόνο εκτέλεσης της κάθε διεργασίας είναι:
  
   Τ(process_params, number_of_groups) = 100/number_of_groups/number_of_processes_in_group(process_params.group_name) 


## Βήματα Υλοποίησης
1. **Κατανόηση συστηματος**
 
      **Η Λειτουργία schedule**
       Η schedule(void) είναι η κύρια λειτουργία του Scheduler στον πυρήνα του Linux.Αυτή η λειτουργία καθορίζει ποια διεργασία θα εκτελεστεί επόμενη στον επεξεργαστή. Λειτουργεί με βάση την πολιτική χρονοδρομολόγησης που έχει οριστεί, επιλέγοντας την κατάλληλη διεργασία από την ουρά εκτέλεσης.


      **Run Queue**
       Η Run Queue είναι μια δομή δεδομένων που χρησιμοποιείται από τον Scheduler για τη διαχείριση των διεργασιών που πρέπει να εκτελεστούν.Περιέχει πληροφορίες για τις διεργασίες που είναι έτοιμες για εκτέλεση ή περιμένουν να εκτελεστούν.Ο Scheduler επιλέγει τις διεργασίες από αυτή την ουρά με βάση την τρέχουσα πολιτική χρονοδρομολόγησης.


      **Sched_entity**
       Η δομή Sched_entity αντιπροσωπεύει μια αφαιρετική ενότητα που μπορεί να χρονοδρομολογηθεί από τον Scheduler.Περιλαμβάνει πληροφορίες σχετικές με την εκτέλεση της διεργασίας, όπως τον χρόνο εκτέλεσης, τις προτεραιότητες και τις καταστάσεις ύπνου.Η Sched_entity είναι κρίσιμη για την υλοποίηση πιο σύνθετων πολιτικών χρονοδρομολόγησης, καθώς παρέχει την ευελιξία στον Scheduler να διαχειριστεί διάφορες πτυχές της διεργασίας.


      **Completely Fair Scheduling**
       Το Completely Fair Scheduling (CFS) είναι μια πολιτική χρονοδρομολόγησης που εστιάζει στη δίκαιη κατανομή του χρόνου επεξεργαστή μεταξύ των διεργασιών.Στοχεύει στην εξισορρόπηση του χρόνου επεξεργασίας, διασφαλίζοντας ότι κάθε διεργασία λαμβάνει ένα δίκαιο μερίδιο του διαθέσιμου χρόνου.Η λειτουργία του CFS βασίζεται στη χρήση ενός εικονικού χρόνου για να καθορίσει πόσο χρόνο έχει αφιερώσει κάθε διεργασία και να ρυθμίσει την προτεραιότητά της ανάλογα.



1. **Δημιουργία νεας πολιτικης**
   - To take μου στην λογικη της ασκησης ηταν να
  

2. **Δοκιμες**
   - Κυριος σκοπος ηταν να μπορεσω να   


3. **Χρηση αλλαγων απο την ασκ3**
   - Η set / get params που εγραψα στην ασκ3 ηταν βασικη στην λογικη μου διοτι με μια μικρη αλλαγη που καταφερα να κανω , μπορουσα να δινω στα demo programs εναν set χρονο σε ticks ωστε να μπορει ενα προγραμμα να ξεκιναει και να τελειωνει σε set χρονο. 
   --(dparams)
     #ifndef _DPARAMS_H
#define _DPARAMS_H

struct d_params {
    char group_name;
    int member_id;
    unsigned long time_in_ticks;   <--νεο πεδιο για τον χρονο
};
#endif /* _DPARAMS_H */

  --(setparams)
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

    // Assuming task_struct has these fields directly
    task->group_name = group_name;
    task->member_id = member_id;
    task->time_in_ticks = time_in_ticks;

    // Assuming task_struct has a pointer to group_info
    task->sched_group_info  = get_or_create_group(group_name); // Ensure this function is declared in a header file included here

    task_unlock(task);
    
    return 0;
}

Στην αλλαγμένη έκδοση της sys_set_task_params, πρόσθεσα τη δυνατότητα να ορίζω ένα χρονικό διάστημα σε ticks για κάθε διεργασία. Αυτή η προσθήκη είναι κρίσιμη για τη λειτουργία της νέας πολιτικής μου, καθώς επιτρέπει στις διεργασίες να έχουν ένα προκαθορισμένο χρονικό διάστημα εκτέλεσης. Εισήγαγα ένα νέο πεδίο time_in_ticks στη δομή task_struct του πυρήνα, το οποίο αποθηκεύει τον χρόνο εκτέλεσης που έχει οριστεί για κάθε διεργασία.Ενημέρωσα τη task_struct με τις τιμές group_name, member_id, και time_in_ticks που παρέχονται από την κλήση της συνάρτησης.

--(getparams)
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

Στην sys_get_task_params, πρόσθεσα τη δυνατότητα να ανακτώ το χρονικό διάστημα σε ticks που έχει οριστεί για κάθε διεργασία, εκτός από το group id και το member id. Ενσωμάτωσα κώδικα για να αντιγράψω τις τιμές group_name, member_id, και time_in_ticks από την task_struct στη δομή d_params που επιστρέφεται στον χρήστη. Χρησιμοποίησα τη συνάρτηση copy_to_user για να αντιγράψω ασφαλώς τις τιμές στο χώρο διεργασιών του χρήστη.
  
   - 
5. **Τροποποίηση των αρχειων**
   - Στην υλοποιηση μου αποσκοπουσα μονο να μετατρεψω καποια αρχεια του kernel χωρις να προσθεσω νεα αρχεια , συγκεκριμενα τα
     - sched.c
     - sched.h
     - syscalls.h
       (απο εδω και κατω αρχεια που θα επρεπε να μετατρεψω αλλα δεν μετετρεψα)
     - Kconfig
     - sched_rt.c
     - process.c
   
   


## Προβλήματα και Λύσεις
 Το κυριο θεμα ηταν πως ο sched.c δεν μπορουσε να αναγνωρισει καποια structs απο το sched.h
Κατά τη διάρκεια της προσπάθειάς μου να συγκεντρώσω τον πυρήνα του Linux για την υλοποίηση της πολιτικής "Group Fairness", αντιμετώπισα σημαντικά προβλήματα συντακτικού και λογικής στον κώδικα, τα οποία επηρέασαν άμεσα τη δομή task_struct. Τα σφάλματα αυτά εμπόδισαν την ολοκλήρωση της άσκησης. Αναλυτικά, τα σφάλματα που εμφανίστηκαν ήταν τα εξής:

Σφάλματα Αναφοράς σε Ανολοκλήρωτους Τύπους στην task_struct:

Τα μηνύματα λάθους όπως το error: dereferencing pointer to incomplete type αποτελούν ένδειξη ότι προσπάθησα να προσπελάσω ή να τροποποιήσω μέλη της δομής task_struct μέσω ενός δείκτη που δεν ήταν πλήρως ορισμένος. Αυτό πιθανότατα συνέβη επειδή προσέθεσα νέα πεδία στη δομή task_struct, όπως το group_name, member_id, και time_in_ticks, αλλά δεν διασφάλισα την ορθή αναφορά και ενημέρωση αυτών των πεδίων σε όλο τον πυρήνα.
Προειδοποιήσεις για Ασυμβατότητα Τύπων:

Προειδοποιήσεις όπως warning: initialization from incompatible pointer type δείχνουν ότι ίσως χρησιμοποίησα λανθασμένους τύπους δεικτών ή δεν χειρίστηκα σωστά τις μετατροπές τύπων. Αυτό θα μπορούσε να συνδέεται με τις αλλαγές που έκανα στη δομή task_struct.
Σφάλματα στον Κώδικα:

Σφάλματα όπως το error: dereferencing pointer to incomplete type στη συνάρτηση calculate_time_slice στο αρχείο include/linux/sched.h υποδηλώνουν ότι οι τροποποιήσεις που προσπάθησα να εφαρμόσω στη δομή task_struct δεν ήταν συμβατές με την υπόλοιπη δομή και λογική του πυρήνα.


## Προσπαθειες επιλυσης
Οσες δοκιμες εγιναν για αλλαγη και διορθωση του κωδικα αναδειχθησαν ανουσιες καθως το linking θεμα ηταν ριζωμενο αλλου και η ριζικη διορθωση του θα απαιτουσε υποχωρηση σε εκδωση με αλλαγμενο get και set πριν τις αλλαγες στο syscall , sched.c , .h αλλα δεν κρατηθηκε πουθενα backup και λογω χρονου δεν εγινε η προσπαθεια
Εφοσον δεν μπορυσε καν να κανει compile δεν δωθηκε και bzimage


## Παραπομπές
https://en.wikipedia.org/wiki/Linux_kernel
https://man7.org/linux/man-pages/man7/sched.7.html
https://www.baeldung.com/linux/real-time-process-scheduling
https://docs.kernel.org/scheduler/sched-rt-group.html
